import React from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

const RequireAuth = ({ allowedRoles }) => {
    const { auth } = useAuth();
    const location = useLocation();

    // auth?.roles?.find(role => allowedRoles?.includes(role))

    if (auth.roles === allowedRoles) {
        return <Outlet />
    } else {
        if (auth?.accessCookies) {
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