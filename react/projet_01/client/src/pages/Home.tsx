import React from 'react'
import AddGame from '../components/games/AddGame'



export default function Home() {
    return (
        <div className="flex justify-center items-center h-screen bg-gradient-to-r from-cyan-500 to-blue-500">
            <AddGame />
        </div>
    )
}
