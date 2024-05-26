import React from 'react'
import { NavLink } from 'react-router-dom'

export default function ErrorPage() {

  return (
    <div className="min-h-screen flex flex-col justify-center items-center bg-gradient-to-r from-cyan-500 to-blue-500">
        <h1 className="text-4xl text-center mt-8 text-white font-bold">Oops ! Something went wrong</h1>
        <h2 className="text-4xl text-center mt-8 text-white font-bold">Try to refresh the page or go back to the <NavLink to={"/"} className="underline">home page</NavLink></h2>
    </div>
  )
}
