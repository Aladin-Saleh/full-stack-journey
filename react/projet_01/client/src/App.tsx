import React from 'react';
import { LoginRegisterPage } from './pages/LoginRegisterPage';
import { useAuth } from './components/providers/AuthProvider';


export function App()
{

    const { user, logout } = useAuth();
    

    if (!user) return <LoginRegisterPage />

    return (
        <div>
            Your are connected !
        </div>
    )
}