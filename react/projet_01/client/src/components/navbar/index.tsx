import React from 'react'
import { NavLink } from 'react-router-dom'

export default function index() {
  return (
    <div className="flex justify-center items-center bg-gradient-to-r from-cyan-500 to-blue-500">
        <NavLink to="/" className="text-white text-2xl p-4">Home </NavLink>
        <NavLink to="/profil" className="text-white text-2xl">Profil</NavLink>
    </div>
  )
}
