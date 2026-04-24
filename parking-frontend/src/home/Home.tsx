import { useSelector } from "react-redux";
import About from "./About";
import { getLoggedUser } from "../store/userSlice";
import MyBookings from "./MyBookings";

export default function Home() {
    const currentUser = useSelector(getLoggedUser)

    return (
        <>
            {!currentUser ? <About /> : <MyBookings />}
        </>
    )
}