import { html } from 'htm/preact';
import { useState } from 'preact/hooks';
import { useTaskContext } from '../context/TaskContext.js';

export function TaskForm() {
  const { addTask } = useTaskContext();
  const [title, setTitle] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [category, setCategory] = useState('personal'); // Default category

  const categories = ['personal', 'work', 'shopping', 'health'];

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim() || !dueDate) return;

    addTask({
      title,
      dueDate,
      status: 'pending',
      category
    });

    setTitle('');
    setDueDate('');
  };

  return html`
        <form class="task-form" onSubmit=${handleSubmit}>
            <input
                type="text"
                placeholder="Task title"
                value=${title}
                onChange=${e => setTitle(e.target.value)}
            />
            <input
                type="date"
                value=${dueDate}
                onChange=${e => setDueDate(e.target.value)}
            />
            <select value=${category} onChange=${e => setCategory(e.target.value)}>
                ${categories.map(cat => html`
                    <option value=${cat}>${cat}</option>
                `)}
            </select>
            <button type="submit">Add Task</button>
        </form>
    `;
}