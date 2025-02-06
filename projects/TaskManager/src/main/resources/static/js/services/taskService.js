import { IndexedDBStorage } from './IndexedDBStorage.js';
import { RestApiClient } from './RestApiClient.js';

// Choose which implementation to use
const storage = new IndexedDBStorage();
// const storage = new RestApiClient();

// Export the storage implementation as the taskService
export const taskService = storage;
