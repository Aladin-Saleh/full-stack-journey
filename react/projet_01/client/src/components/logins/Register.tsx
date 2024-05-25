import React from 'react'
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import axios from 'axios';


export default function Register(props: any) {

    const [message, setMessage] = React.useState("")

    const handleSubmit = (e: any) => {
        e.preventDefault();

        axios({
            url: `${process.env.REACT_APP_API_URL}auth/register`,
            method: 'POST',
            data: {
                email: props.email,
                username: props.username,
                password: props.password
            },
            withCredentials: true
        })
        .then((res) => setMessage(res.data.message))
        .catch((err) => setMessage(err.response.data.reason?.username || err.response.data.reason?.email || err.response.data.reason?.password));
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
                label="Username"
                variant="outlined"
                onChange={(e) => props.setUsername(e.target.value)}
                value={props.username}
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
                Register
            </Button>
        </form>
    )
}
