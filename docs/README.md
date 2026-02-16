# Steve Chatbot User Guide

## Introduction

Steve is a simple chatbot that helps you manage your tasks. You can add, delete, mark, and unmark tasks. You can also list all your tasks and find tasks by keyword.

## Commands

### Adding a task

#### `todo`
Adds a todo task.
Format: `todo <description>`
Example: `todo read book`

#### `deadline`
Adds a deadline task.
Format: `deadline <description> /by <yyyy-mm-dd>`
Example: `deadline return book /by 2024-09-22`

#### `event`
Adds an event task.
Format: `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
Example: `event project meeting /from 2024-09-23 /to 2024-09-24`

### Listing tasks
#### `list`
Lists all tasks.
Format: `list`

### Marking and unmarking tasks
#### `mark`
Marks a task as done.
Format: `mark <task number>`
Example: `mark 1`

#### `unmark`
Marks a task as not done.
Format: `unmark <task number>`
Example: `unmark 1`

### Deleting a task
#### `delete`
Deletes a task.
Format: `delete <task number>`
Example: `delete 1`

### Finding a task
#### `find`
Finds tasks containing a keyword.
Format: `find <keyword>`
Example: `find book`

### Exiting the program
#### `bye`
Exits the program.
Format: `bye`
