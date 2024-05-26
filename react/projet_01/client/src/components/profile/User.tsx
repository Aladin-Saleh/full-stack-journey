import React from 'react'
import Button from '@mui/material/Button';

export default function User(props: any) {


    return (
        <div>
            {props.user && (
                <div className="absolute top-0 left-0 p-4">
                    <div className="bg-white p-8 rounded-2xl shadow-lg">
                        <div className="flex flex-col items-start space-y-4">
                            <div>
                                <span className="font-semibold text-lg">{props.user.username}</span>
                                <p>Number of games in the collection: {props.user.collection.length}</p>
                            </div>
                            <div>
                                <Button
                                    onClick={props.logout}
                                    variant="contained"
                                    style={{ backgroundColor: 'red' }}
                                    className="text-sm px-2 py-1">
                                    Logout
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>

    )
}
