import { createSlice, type PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from './store'

export type User = {
    username: string,
    email: string | null,
    accessToken: string
} | null

type UserState = {
    currentUser: User
}

const initialState: UserState = {
    currentUser: null
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        login: (state, action: PayloadAction<User>) => {
            state.currentUser = action.payload
        },
        logout: (state) => {
            state.currentUser = null
        },
    },
})

export const getLoggedUser = (state: RootState) => state.user.currentUser

export const {login, logout} = userSlice.actions;
export default userSlice.reducer