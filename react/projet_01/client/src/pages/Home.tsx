import React, { useEffect } from 'react'
import AddGame from '../components/games/AddGame'
import { useAuth } from '../components/providers/AuthProvider'
import { LoginRegisterPage } from './LoginRegisterPage';


export default function Home() {

    const { user, logout } = useAuth();


    if (!user) return <LoginRegisterPage />



    return (
        <div className="flex justify-center items-center h-screen bg-gradient-to-r from-cyan-500 to-blue-500">
            <AddGame />
        </div>
    )
}
