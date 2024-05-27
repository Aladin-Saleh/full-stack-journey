import React from 'react'
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import axios from 'axios';
import { AuthContext } from '../providers/AuthProvider';
import { useState, useContext } from 'react';

export default function Login(props: any) {

    const [message, setMessage] = useState("")
    const { login } = useContext(AuthContext);
 
    const handleSubmit = (e: any) => {
        e.preventDefault();
        login(props.email, props.password)
        
    };



    return (
        <form className="flex flex-col space-y-4">
            <TextField
                label="Email Address"
                variant="outlined"
                onChange={(e) => props.setEmail(e.target.value)}
                value={props.email}
            />
            <TextField
                label="Password"
                variant="outlined"
                type="password"
                onChange={(e) => props.setPassword(e.target.value)}
                value={props.password}
            />
            <p className='text-center'>{message}</p>
            <Button variant="contained" style={{ backgroundColor: '#007FFF' }} className="mt-4" onClick={handleSubmit}>
                Login
            </Button>
        </form>

    )
}
