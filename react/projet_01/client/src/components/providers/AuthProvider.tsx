import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';


export const AuthContext = createContext<any>(null);

export function useAuth() { return useContext(AuthContext); }

export default function AuthProvider({ children }: any) {

    const [user, setUser] = useState(null);

    const login = async (email:string, password:string) => {
        try {
            const response = await axios({
                url: `${process.env.REACT_APP_API_URL}auth/login`,
                method: 'POST',
                data: {
                    email: email,
                    password: password
                },
                withCredentials: true
            })
            console.log(response.data.user);
            
            setUser(response.data.user);
            window.location.replace('/');
        } catch (error) {
            console.log("Login Failed ", error)
            
        }

    }

    const logout = async () => {
        try {
            const response = await axios({
                url: `${process.env.REACT_APP_API_URL}auth/logout`,
                method: 'GET',
                withCredentials: true
            })
            setUser(null);
        } catch (error) {
            setUser(null);            
        }

    }

    const checkUserLoggedIn = async () => {
        try {
          const response = await axios.get(`${process.env.REACT_APP_API_URL}jwtid`, { withCredentials: true });
          console.log(response);
          
          setUser(response.data.user);
        } catch {
          setUser(null);
          window.location.replace('/auth');
        }
    };

    useEffect(() => {
        checkUserLoggedIn();
    }, []);



    return (
        <AuthContext.Provider value={{ user, login, logout }}>
          {children}
        </AuthContext.Provider>
      );

}

