import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

type User = {
    username: string,
    email: string
} | null

type UserState = {
    user: User
}

const initialState: UserState = {
    user: null
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        login: (state, action: PayloadAction<User>) => {
            state.user = action.payload
        },
        logout: (state) => {
            state.user = null
        },
    },
})

export const {login, logout} = userSlice.actions;
export default userSlice.reducer