import { createContext } from 'preact';
import { useState, useContext, useEffect } from 'preact/hooks';
import { html } from 'htm/preact';
import { taskService } from '../services/taskService.js';

const TaskContext = createContext();

export function TaskProvider({ children }) {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  // Load tasks on mount
  useEffect(() => {
    const loadTasks = async () => {
      try {
        const loadedTasks = await taskService.getAllTasks();
        setTasks(loadedTasks);
      } catch (error) {
        console.error('Error loading tasks:', error);
      } finally {
        setLoading(false);
      }
    };
    loadTasks();
  }, []);

  const addTask = async (task) => {
    try {
      const newTask = await taskService.createTask(task);
      setTasks([...tasks, newTask]);
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  const deleteTask = async (id) => {
    try {
      await taskService.deleteTask(id);
      setTasks(tasks.filter(task => task.id !== id));
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  const updateTask = async (id, updatedTask) => {
    try {
      const updated = await taskService.updateTask(id, updatedTask);
      setTasks(tasks.map(task => task.id === id ? updated : task));
    } catch (error) {
      console.error('Error updating task:', error);
    }
  };

  if (loading) {
    return html`<div>Loading tasks...</div>`;
  }

  return html`
        <${TaskContext.Provider} value=${{ tasks, addTask, deleteTask, updateTask }}>
            ${children}
        <//>
    `;
}

export function useTaskContext() {
  return useContext(TaskContext);
}
