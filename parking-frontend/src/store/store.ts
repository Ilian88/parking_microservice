import { configureStore } from '@reduxjs/toolkit'

import vehicleReducer from './vehicleSlice'
import userReducer from './userSlice'

export const store = configureStore({
    reducer: {
        vehicle: vehicleReducer,
        user: userReducer
    }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch