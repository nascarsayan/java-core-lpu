# type: ignore
from collections import defaultdict
from typing import List

class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        # Email to name mapping
        email_to_name = {}
        # Graph of email connections
        graph = defaultdict(set)
        
        # Build the graph
        for account in accounts:
            name = account[0]
            # Use Python's set operations for first email
            emails = set(account[1:])
            # Map all emails to name
            for email in emails:
                email_to_name[email] = name
                # If this is not the first email in account, connect it with first email
                if account[1]:
                    graph[account[1]].update(emails - {account[1]})
                    for e in emails - {account[1]}:
                        graph[e].add(account[1])
        
        # DFS to find connected components
        def dfs(email: str, visited: set, component: list) -> None:
            visited.add(email)
            component.append(email)
            for neighbor in graph[email]:
                if neighbor not in visited:
                    dfs(neighbor, visited, component)
        
        # Find all connected components
        visited = set()
        result = []
        
        for email in graph:
            if email not in visited:
                component = []
                dfs(email, visited, component)
                # Add name and sort emails
                result.append([email_to_name[email]] + sorted(component))
        
        return result
