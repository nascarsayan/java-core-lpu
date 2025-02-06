// Interface for Task Storage implementations
export class TaskStorageInterface {
  async createTask(task) {
    throw new Error('Not implemented');
  }

  async getAllTasks() {
    throw new Error('Not implemented');
  }

  async updateTask(id, task) {
    throw new Error('Not implemented');
  }

  async deleteTask(id) {
    throw new Error('Not implemented');
  }

  async getTasksByUserId(userId) {
    throw new Error('Not implemented');
  }
}