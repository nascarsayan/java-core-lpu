import { render } from 'preact';
import { html } from 'htm/preact';
import { TaskList } from './components/TaskList.js';
import { TaskForm } from './components/TaskForm.js';
import { TaskProvider } from './context/TaskContext.js';

function App() {
  return html`
        <div class="container">
            <header class="header">
                <h1>Task Manager</h1>
            </header>
            <${TaskProvider}>
                <${TaskForm} />
                <${TaskList} />
            <//>
        </div>
    `;
}

render(html`<${App} />`, document.getElementById('app'));
