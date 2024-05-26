import React from 'react'
import { useAuth } from '../components/providers/AuthProvider'
import { LoginRegisterPage } from './LoginRegisterPage'
import User from '../components/profile/User'
import GameCollection from '../components/profile/GameCollection'
import AddGame from '../components/games/AddGame'
import ShowGames from '../components/games/ShowGames'

export default function Profile() {


    const { user, logout } = useAuth();

    if (!user) return <LoginRegisterPage />



    return (

        <div className="flex justify-center items-center h-screen bg-gradient-to-r from-cyan-500 to-blue-500">
            {/* <AddGame /> */}
            {user.collection.length > 0 ? (
                <div className="flex flex-wrap justify-center items-center overflow-auto max-w-9xl mx-auto my-4" style={{ maxHeight: '800px' }}>
                    <ShowGames games={user.collection} />
                </div>
            ) : (<div className="text-white text-lg font-bold">No games in collection, start adding some!</div>)}
            <User user={user} logout={logout} />
        </div>
    )
}
