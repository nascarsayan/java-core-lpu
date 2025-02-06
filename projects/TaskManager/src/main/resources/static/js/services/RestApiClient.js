export class RestApiClient {
  constructor(baseUrl = '/api') {
    this.baseUrl = baseUrl;
  }

  async createTask(task) {
    try {
      const response = await fetch(`${this.baseUrl}/tasks`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(task)
      });
      if (!response.ok) throw new Error('Failed to create task');
      return response.json();
    } catch (error) {
      console.error('Error creating task:', error);
      throw error;
    }
  }

  async getAllTasks() {
    try {
      const response = await fetch(`${this.baseUrl}/tasks`);
      if (!response.ok) throw new Error('Failed to fetch tasks');
      return response.json();
    } catch (error) {
      console.error('Error fetching tasks:', error);
      throw error;
    }
  }

  async updateTask(id, task) {
    try {
      const response = await fetch(`${this.baseUrl}/tasks/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(task)
      });
      if (!response.ok) throw new Error('Failed to update task');
      return response.json();
    } catch (error) {
      console.error('Error updating task:', error);
      throw error;
    }
  }

  async deleteTask(id) {
    try {
      const response = await fetch(`${this.baseUrl}/tasks/${id}`, {
        method: 'DELETE'
      });
      if (!response.ok) throw new Error('Failed to delete task');
      return true;
    } catch (error) {
      console.error('Error deleting task:', error);
      throw error;
    }
  }

  async getTasksByUserId(userId) {
    try {
      const response = await fetch(`${this.baseUrl}/tasks?userId=${userId}`);
      if (!response.ok) throw new Error('Failed to fetch user tasks');
      return response.json();
    } catch (error) {
      console.error('Error fetching user tasks:', error);
      throw error;
    }
  }
}