import { html } from 'htm/preact';
import { useState } from 'preact/hooks';
import { useTaskContext } from '../context/TaskContext.js';

export function TaskList() {
    const { tasks, deleteTask, updateTask } = useTaskContext();
    const [selectedCategory, setSelectedCategory] = useState('all');

    const categories = ['all', 'personal', 'work', 'shopping', 'health'];

    const filteredTasks = selectedCategory === 'all'
        ? tasks
        : tasks.filter(task => task.category === selectedCategory);

    return html`
        <div class="task-list-container">
            <div class="category-filter">
                <select value=${selectedCategory} onChange=${e => setSelectedCategory(e.target.value)}>
                    ${categories.map(cat => html`
                        <option value=${cat}>${cat}</option>
                    `)}
                </select>
            </div>
            <div class="task-list">
                ${filteredTasks.map(task => html`
                    <div key=${task.id} class="task-item">
                        <div>
                            <h3>${task.title}</h3>
                            <p>Due: ${new Date(task.dueDate).toLocaleDateString()}</p>
                            <p>Status: ${task.status}</p>
                            <p>Category: ${task.category}</p>
                        </div>
                        <div class="actions">
                            <button onClick=${() => updateTask(task.id, { ...task, status: task.status === 'pending' ? 'completed' : 'pending' })}>
                                ✓
                            </button>
                            <button onClick=${() => deleteTask(task.id)}>🗑</button>
                        </div>
                    </div>
                `)}
            </div>
        </div>
    `;
}