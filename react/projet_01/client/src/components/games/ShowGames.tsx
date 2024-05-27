import React from 'react'
import GameCard from './GameCard'

export default function ShowGames(props: any) {


    console.log(props.games);
    

    return (
        <>
            {props.games.length > 0 ? (
                props?.games?.map((game: any) => (
                    <div key={game?.id || game.gameInfo?.id} className="mx-2 my-4 text-center"> 
                        <GameCard game={game} openModal={props.openModal}/>
                    </div>
                ))
            ) : (
                <div className="text-center text-2xl">No games found in your collection ! Go add some !</div>
            )}
        </>



    )
}
