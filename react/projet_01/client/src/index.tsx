import ReactDOM from "react-dom/client";
import { App } from "./App";
import "./index.css";
import AuthProvider from "./components/providers/AuthProvider";


const reactRoot = ReactDOM.createRoot(document.getElementById("root") as HTMLElement);

reactRoot.render(
    <AuthProvider>
        <App />
    </AuthProvider>
 );
