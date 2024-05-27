import { createBrowserRouter } from "react-router-dom";
import { LoginRegisterPage } from "../../pages/LoginRegisterPage";
import Profile from "../../pages/Profile";
import Home from "../../pages/Home";
import ErrorPage from "../../pages/ErrorPage";
import Navbar from "../navbar";



const router = createBrowserRouter([

    
    {
        path: "/auth",
        element: <><LoginRegisterPage /></>,
        errorElement: <ErrorPage />
    },
    {
        path: "/profil",
        element: <><Navbar /><Profile /></>
    },
    {
        path: "/",
        element: <><Navbar /><Home /></>
    },
    {
        path: "*",
        element: <ErrorPage />
    }
])

export default router;