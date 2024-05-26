import React, { useEffect } from 'react'
import TextField from '@mui/material/TextField';
import { useState } from 'react';
import axios from 'axios';
import ShowGame from './ShowGames'

export default function AddGame() {

    const [search, setSearch] = useState('')
    const [game, setGame] = useState<any>([])

    async function getGameByName() {

        try {
            const response = await axios({
                method: 'GET',
                url: `http://localhost:3081/ext/games/${search}`,
                headers: {
                    'Content-Type': 'application/json'
                },
                withCredentials: true
            })
            console.log(response.data);
            setGame(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        if (search.length % 3 === 0) getGameByName(); // Only search every 3 characters so we don't spam the API
    }, [search])





    return (
        <div className="min-h-screen flex flex-col">
            <div className="w-full flex justify-center items-start pt-8">
                <TextField
                    fullWidth={true}
                    label="Search for a game"
                    variant="outlined"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    className="max-w-xl bg-white rounded-md shadow-md p-2 text-center text-lg font-bold"
                />
            </div>
            {game.length > 0 ? (
                <div className="flex flex-wrap justify-center items-center overflow-auto max-w-9xl mx-auto my-4" style={{maxHeight: '800px'}}>
                    <ShowGame games={game} />
                </div>

            ) : null}
        </div>


    )
}
