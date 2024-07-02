import { Task } from "./task";

export interface Todolist {
    id: string;
    tasks: Task[];
    userId: string;
}
