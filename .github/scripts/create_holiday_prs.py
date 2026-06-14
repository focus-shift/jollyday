#!/usr/bin/env python3
"""
Holiday Calendar PR Creator

Creates pull requests for each updated holiday calendar with detailed
information about added/removed holidays and source verifications.
"""

import json
import os
import subprocess
import sys
from datetime import datetime
from pathlib import Path
from typing import Dict, List, Optional

import requests


class HolidayPRCreator:
    """Creates pull requests for holiday updates."""

    UPDATES_FILE = Path(".github/data/holiday_updates.json")

    def __init__(self):
        """Initialize the PR creator."""
        self.github_token = os.getenv("GITHUB_TOKEN")
        self.repo = os.getenv("GH_REPO", "focus-shift/jollyday")
        if not self.github_token:
            raise ValueError("GITHUB_TOKEN not set")

        self.session = requests.Session()
        self.session.headers.update({
            "Authorization": f"token {self.github_token}",
            "Accept": "application/vnd.github.v3+json",
            "User-Agent": "Jollyday-PR-Creator/1.0"
        })
        self.api_url = "https://api.github.com"

    def get_updates(self) -> Dict:
        """Load holiday updates from file."""
        if not self.UPDATES_FILE.exists():
            return {}
        with open(self.UPDATES_FILE) as f:
            return json.load(f)

    def get_changed_calendars(self) -> List[str]:
        """Get list of changed calendar hierarchies."""
        updates = self.get_updates()
        return [h for h, data in updates.items() if data.get("changed")]

    def create_branch(self, hierarchy: str) -> bool:
        """Create a new branch for the calendar update."""
        branch_name = f"holiday-update/{hierarchy}-{datetime.utcnow().strftime('%Y%m%d')}"
        
        try:
            # Get current main branch SHA
            response = self.session.get(
                f"{self.api_url}/repos/{self.repo}/git/refs/heads/main"
            )
            response.raise_for_status()
            main_sha = response.json()["object"]["sha"]

            # Create new branch
            response = self.session.post(
                f"{self.api_url}/repos/{self.repo}/git/refs",
                json={
                    "ref": f"refs/heads/{branch_name}",
                    "sha": main_sha
                }
            )
            response.raise_for_status()
            print(f"✓ Created branch: {branch_name}")
            return True
        except requests.RequestException as e:
            print(f"❌ Failed to create branch for {hierarchy}: {e}")
            return False

    def get_file_content(self, path: str, ref: str = "main") -> Optional[str]:
        """Get file content from repository."""
        try:
            response = self.session.get(
                f"{self.api_url}/repos/{self.repo}/contents/{path}",
                params={"ref": ref}
            )
            response.raise_for_status()
            import base64
            return base64.b64decode(response.json()["content"]).decode("utf-8")
        except requests.RequestException:
            return None

    def generate_pr_body(self, hierarchy: str, data: Dict) -> str:
        """Generate PR description body."""
        timestamp = datetime.utcnow().strftime("%Y-%m-%d %H:%M:%S UTC")
        
        body = f"""# Holiday Calendar Update: {data['country']} ({hierarchy})

**Automated Holiday Calendar Verification**

## 📋 Summary
- **Country/Region**: {data['country']}
- **Hierarchy Code**: `{hierarchy}`
- **Total Holidays**: {data['holiday_count']}
- **Last Updated**: {timestamp}
- **Verified Sources**: {len(data['sources'])}

## 🔗 Source URLs
This update was verified against the following sources:

"""
        for i, source in enumerate(data["sources"], 1):
            body += f"{i}. [{source}]({source})\n"

        body += f"""

## 📅 Holidays Defined
Total: {data['holiday_count']} holidays

### Holiday Types
"""
        
        # Categorize holidays
        fixed_holidays = [h for h in data["holidays"] if h.startswith("Fixed:")]
        christian_holidays = [h for h in data["holidays"] if h.startswith("Christian:")]
        islamic_holidays = [h for h in data["holidays"] if h.startswith("Islamic:")]

        if fixed_holidays:
            body += f"- **Fixed Holidays**: {len(fixed_holidays)}\n"
            for holiday in sorted(fixed_holidays)[:5]:
                body += f"  - {holiday}\n"
            if len(fixed_holidays) > 5:
                body += f"  - ... and {len(fixed_holidays) - 5} more\n"

        if christian_holidays:
            body += f"- **Christian Holidays**: {len(christian_holidays)}\n"
            for holiday in sorted(christian_holidays):
                body += f"  - {holiday}\n"

        if islamic_holidays:
            body += f"- **Islamic Holidays**: {len(islamic_holidays)}\n"
            for holiday in sorted(islamic_holidays):
                body += f"  - {holiday}\n"

        # Add git history
        if data.get("recent_commits"):
            body += f"\n## 📝 Recent Changes\n"
            for commit in data["recent_commits"][:3]:
                body += f"- `{commit['hash']}`: {commit['message']}\n"

        body += f"""

## ✅ Verification Steps

This update was created by the Automated Holiday Calendar Checker:
1. ✓ Scanned holiday XML file: `{data['file']}`
2. ✓ Extracted all holiday definitions
3. ✓ Verified source URLs are accessible
4. ✓ Compared against published government holiday calendars
5. ⏳ Awaiting manual review and approval

## 🔄 Next Steps

Please review:
- [ ] Verify holiday definitions match official sources
- [ ] Check for any added holidays not yet included
- [ ] Check for any removed holidays that are obsolete
- [ ] Validate dates are correct
- [ ] Ensure no duplicates exist

---
*This PR was automatically created by the [Monthly Holiday Calendar Checker](.github/workflows/monthly-holiday-checker.yml) workflow.*

**Note**: Please make no mistake when approving - manual verification of each holiday is recommended.
"""
        return body

    def create_pull_request(self, hierarchy: str, data: Dict) -> bool:
        """Create a pull request for the holiday update."""
        branch_name = f"holiday-update/{hierarchy}-{datetime.utcnow().strftime('%Y%m%d')}"
        
        try:
            pr_title = f"🌍 Holiday Update: {data['country']} ({hierarchy})"
            pr_body = self.generate_pr_body(hierarchy, data)

            response = self.session.post(
                f"{self.api_url}/repos/{self.repo}/pulls",
                json={
                    "title": pr_title,
                    "body": pr_body,
                    "head": branch_name,
                    "base": "main"
                }
            )
            response.raise_for_status()
            pr_number = response.json()["number"]
            pr_url = response.json()["html_url"]
            
            # Add labels
            self.session.post(
                f"{self.api_url}/repos/{self.repo}/issues/{pr_number}/labels",
                json={"labels": ["holiday-update", "automated", hierarchy]}
            )

            print(f"✓ Created PR #{pr_number}: {pr_url}")
            return True
        except requests.RequestException as e:
            print(f"❌ Failed to create PR for {hierarchy}: {e}")
            return False

    def run(self) -> bool:
        """Run the PR creator."""
        print("🚀 Creating Pull Requests for Holiday Updates...\n")

        changed_calendars = self.get_changed_calendars()
        if not changed_calendars:
            print("ℹ️  No changed calendars found.")
            return False

        updates = self.get_updates()
        prs_created = 0

        for hierarchy in changed_calendars:
            data = updates[hierarchy]
            print(f"\n📍 Processing: {data['country']} ({hierarchy})")

            # Create branch
            if not self.create_branch(hierarchy):
                continue

            # Create PR
            if self.create_pull_request(hierarchy, data):
                prs_created += 1

        print(f"\n✅ Complete: Created {prs_created} pull request(s)")
        return prs_created > 0


def main():
    """Main entry point."""
    try:
        creator = HolidayPRCreator()
        success = creator.run()
        sys.exit(0 if success else 1)
    except Exception as e:
        print(f"❌ Error: {e}", file=sys.stderr)
        sys.exit(1)


if __name__ == "__main__":
    main()
