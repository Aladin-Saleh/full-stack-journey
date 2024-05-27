import React, { useEffect } from 'react'
import Button from '@mui/material/Button';
import axios from 'axios';
import { useAuth } from '../providers/AuthProvider';
import { useState } from 'react';
import ModalCardGames from './ModalCardGames';

export default function GameCard(props: any) {


    const { user } = useAuth();
    const [isAdded, setIsAdded] = useState(false);
    const [isModalOpen, setIsModalOpen] = useState(false);


    async function addGameToCollection() {
        try {
            const response = await axios({
                method: 'POST',
                url: `${process.env.REACT_APP_API_URL}collection/add`,
                headers: {
                    'Content-Type': 'application/json'
                },
                withCredentials: true,
                data: {
                    gameInfo: {
                        name: props.game.name,
                        background_image: props.game.background_image,
                        released: props.game.released,
                        rating: props.game.rating,
                        id: props.game.id,
                        genres: props.game.genres
                    }
                }
            })
            console.log(response.data);
            setIsAdded(true);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        user.collection.forEach((game: any) => {
            if (game.gameInfo.id === props.game.id) setIsAdded(true);
        })

    }, [isAdded, user.collection, props.game.id])



    return (
        <div className={`max-w-md w-full h-auto rounded overflow-hidden shadow-lg bg-white min-w-md ${props.openModal && 'cursor-pointer'}`} onClick={() => {props.openModal && setIsModalOpen(true)}}>
            <img src={props.game?.background_image || props.game.gameInfo?.background_image} alt={props.game?.name || props.game.gameInfo?.name} className="w-full h-48 object-cover" />
            <div className="px-6 py-4">
                <div className="font-bold text-xl mb-2">{props.game?.name || props.game.gameInfo?.name}</div>
                <p className="text-gray-700 text-base">
                    Released: {props.game?.released || props.game.gameInfo?.released || 'Unknown'}
                </p>
                <p className="text-gray-600 text-base">
                    Rating: {props.game?.rating || props.game.gameInfo?.rating || 0 } / 5
                </p>

                {props.game?.name && !isAdded && (<Button onClick={addGameToCollection} variant="contained" style={{ backgroundColor: '#007FFF', padding: '10px 20px', fontSize: '1rem' }} className="text-lg px-5 py-2">Add to collection</Button>)}
            </div>
            {isModalOpen && <ModalCardGames game={props.game} setIsModalOpen={setIsModalOpen} />}
        </div>


    )
}
