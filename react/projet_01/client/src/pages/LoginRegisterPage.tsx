import React from 'react'
import { useState } from 'react';

import Login from '../components/logins/Login';
import Register from '../components/logins/Register';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useAuth } from '../components/providers/AuthProvider';

export function LoginRegisterPage() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");

    const [isLogin, setIsLogin] = useState(true);

    const { user } = useAuth();

    if (user) window.location.replace('/profil');

    return (
        <div className="flex justify-center items-center h-screen bg-gradient-to-r from-cyan-500 to-blue-500">
          <div className="bg-white p-8 rounded-2xl shadow-lg max-w-sm w-full">
            <h2 className="text-2xl font-bold text-center mb-6">Login Form</h2>
            <div className="flex justify-around mb-6">
              <Button  onClick={() => { setIsLogin(true) }} variant="contained" style={{ backgroundColor: '#007FFF', padding: '10px 20px', fontSize: '1rem' }} className="text-lg px-5 py-2">Login</Button>
              <Button  onClick={() => { setIsLogin(false) }}variant="contained" style={{ backgroundColor: '#007FFF', padding: '10px 20px', fontSize: '1rem' }} className="text-lg px-5 py-2">Register</Button>
            </div>
                {isLogin ? (<Login email={email} password={password} setEmail={setEmail} setPassword={setPassword}/>) : (<Register email={email} password={password} username={username} setUsername={setUsername} setEmail={setEmail} setPassword={setPassword}/>)}
          </div>
        </div>
      );
    }

