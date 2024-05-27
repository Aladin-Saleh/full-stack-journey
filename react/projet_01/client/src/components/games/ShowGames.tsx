import React from 'react'
import GameCard from './GameCard'

export default function ShowGames(props: any) {




    return (
        <>
            {props.games.map((game: any) => (
                <div key={game.id || game.gameInfo.id} className="mx-2 my-4 text-center"> 
                    <GameCard game={game} openModal={props.openModal}/>
                </div>
            ))}
        </>



    )
}
