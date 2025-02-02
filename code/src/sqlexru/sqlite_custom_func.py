import sqlite3
import http.client


# Custom function to fetch data from an external API and return a hash
def fetch_post_with_id(id: int):
    # Example API endpoint (replace with actual API URL and host)
    conn = http.client.HTTPSConnection("jsonplaceholder.typicode.com")

    # Define the API endpoint and query parameters
    endpoint = f"/posts/{id}"

    # Send the GET request
    conn.request("GET", endpoint)

    # Get the response
    response = conn.getresponse()

    # Check if the response status is OK (200)
    if response.status == 200:
        data = response.read().decode("utf-8")
        return data
    else:
        return "API Error"


# Connect to SQLite
conn = sqlite3.connect(":memory:")

# Register the custom function with SQLite
conn.create_function("fetch_post_with_id", 1, fetch_post_with_id)

# Test the UDF by fetching the post with id=1
cursor = conn.cursor()
cursor.execute("SELECT fetch_post_with_id(1);")
print(cursor.fetchone()[0])  # Output: the fetched data or 'API Error'
