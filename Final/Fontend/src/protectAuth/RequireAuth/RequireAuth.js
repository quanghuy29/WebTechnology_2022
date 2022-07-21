import React from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

const RequireAuth = ({ allowedRoles }) => {
    const { auth } = useAuth();
    const location = useLocation();
    const token = localStorage.getItem('token-auth')

    // auth?.roles?.find(role => allowedRoles?.includes(role))
    console.log(auth.roles === allowedRoles || auth.token === token)
    console.log(auth, allowedRoles)

    if (auth.roles === allowedRoles || auth.token === token) {
        return <Outlet />
    } else {
        if (auth?.token) {
            return <Navigate to="/oops" state={{ from: location }} replace />
        } else {
            return <Navigate to="/login" state={{ from: location }} replace />
        }
    }

    // return (
    //     auth.role == allowedRoles
    //         ? <Outlet />
    //         : auth?.user
    //             ? <Navigate to="/oops" state={{ from: location }} replace />
    //             : <Navigate to="/login" state={{ from: location }} replace />
    // );
}

export default RequireAuth;