import React from 'react'
import { NavLink } from 'react-router-dom'
import { useAuth } from '../providers/AuthProvider';


export default function Navbar() {


  const { user, logout } = useAuth();

  return (

    <div>
      {
        user && (
          <div className="flex justify-center items-center bg-gradient-to-r from-cyan-500 to-blue-500">
            <NavLink to="/" className="text-white text-2xl p-4">Home </NavLink>
            <NavLink to="/profil" className="text-white text-2xl">Profil</NavLink>
          </div>
        )
      }
    </div>
  )
}
