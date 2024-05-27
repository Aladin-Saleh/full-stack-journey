import React, { useEffect } from 'react'
import RatingGame from './RatingGame';

export default function ModalCardGames(props: any) {


    const modalRef = React.useRef(null);

    useEffect(() => {
        const handleClickOutside = (event: any) => {
            if (modalRef.current === event.target) {
                props.setIsModalOpen(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [])



    return (
        <div ref={modalRef} className="fixed inset-0 bg-black bg-opacity-50 backdrop-blur-sm flex justify-center items-center" >
            <div className="max-w-md w-full h-auto rounded overflow-hidden shadow-lg bg-white min-w-md">
                <img src={props.game?.background_image || props.game.gameInfo?.background_image} alt={props.game?.name || props.game.gameInfo?.name} className="w-full h-48 object-cover" />
                <div className="px-6 py-4">
                    <div className="font-bold text-xl mb-2">{props.game?.name || props.game.gameInfo?.name}</div>
                    <p className="text-gray-700 text-base">
                        Released: {props.game?.released || props.game.gameInfo?.released || 'Unknown'}
                    </p>
                    <p className="text-gray-600 text-base">
                        Rating: {props.game?.rating || props.game.gameInfo?.rating || 0} / 5
                    </p>
                    <RatingGame game={props.game}/>

                </div>
            </div>
        </div>
    )
}    