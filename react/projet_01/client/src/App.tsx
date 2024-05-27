import React from 'react';
import { LoginRegisterPage } from './pages/LoginRegisterPage';
import { useAuth } from './components/providers/AuthProvider';
import Profile  from './pages/Profile';
import Navbar from './components/navbar';

export function App() {

    const { user, logout } = useAuth();
    console.log(user);


    if (!user) return <LoginRegisterPage />

    return (
        <div>
        <Navbar />
        </div>
    )
}