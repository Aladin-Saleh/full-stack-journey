import ReactDOM from "react-dom/client";
import { App } from "./App";
import "./index.css";
import AuthProvider from "./components/providers/AuthProvider";

import "./index.css";
import Router  from "./components/routes"
import { RouterProvider } from "react-router-dom";


const reactRoot = ReactDOM.createRoot(document.getElementById("root") as HTMLElement);

reactRoot.render(
    <AuthProvider>
        <RouterProvider router={Router} />
    </AuthProvider>
 );
