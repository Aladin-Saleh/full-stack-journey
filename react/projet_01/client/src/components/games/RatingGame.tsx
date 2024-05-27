import React from 'react'
import axios from 'axios'
import { useState } from 'react'
import Button from '@mui/material/Button'

export default function RatingGame(props: any) {

    const availableStatus = ['souhaite jouer', 'en cours', 'terminé']
    const availableRating = ['0', '1', '2', '3', '4', '5']

    console.log(props.game);
    

    const [gameStatus, setGameStatus] = useState(props.game.gameStatus);
    const [gameRating, setGameRating] = useState(props.game.gameRating);
    const [gameReview, setGameReview] = useState(props.game.gameReview);



    async function updateGameRating() {
        try {
            const response = await axios({
                method: 'PUT',
                url: `${process.env.REACT_APP_API_URL}collection/update/${props.game._id}`,
                headers: {
                    'Content-Type': 'application/json'
                },
                withCredentials: true,
                data: {
                    gameStatus: gameStatus,
                    gameRating: gameRating,
                    gameReview: gameReview
                }
            })
            console.log(response.data);
            window.location.reload();
        } catch (error) {
            console.log(error);
        }
    }


    return (
        // <div>{props.gameId}</div>
        <div>
            <div className="flex justify-between">
                <label htmlFor="status">Status</label>
                <select name="status" id="status" onChange={(e) => setGameStatus(e.target.value)} value={gameStatus}>
                    <option value="">--Choisir un status--</option>
                    {availableStatus.map((status, index) => <option key={index} value={status}>{status}</option>)}
                </select>
            </div>
            <div className="flex justify-between">
                <label htmlFor="rating">Rating</label>
                <select name="rating" id="rating" onChange={(e) => setGameRating(e.target.value)} value={gameRating}>
                    <option value="">--Choisir une note--</option>
                    {availableRating.map((rating, index) => <option key={index} value={rating}>{rating}</option>)}
                </select>
            </div>
            <div className="flex justify-between">
                <label htmlFor="review">Review</label>
                <textarea name="review" id="review" onChange={(e) => setGameReview(e.target.value)} value={gameReview}></textarea>
            </div>
            <br />
            <Button onClick={updateGameRating} variant="contained" style={{ backgroundColor: '#007FFF', padding: '10px 20px', fontSize: '1rem' }} className="text-lg px-5 py-2">Update review !</Button>

        </div>

    )
}
