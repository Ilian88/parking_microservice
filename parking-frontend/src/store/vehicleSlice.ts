import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

type VehicleType = 'car' | 'truck'

type CarData = {
  licensePlate: string
  euroCategory: string
  make: string
  model: string
}

type TruckData = {
  licensePlate: string
  euroCategory: string
  truckMake: string
  truckModel: string
  trailerMake: string
  trailerModel: string
  weight: string
  length: string
}

type VehicleState = {
  vehicleType: VehicleType
  car: CarData
  truck: TruckData
  submitted: boolean
}

const initialState: VehicleState = {
  vehicleType: 'car',
  car: {
    licensePlate: '',
    euroCategory: '',
    make: '',
    model: '',
  },
  truck: {
    licensePlate: '',
    euroCategory: '',
    truckMake: '',
    truckModel: '',
    trailerMake: '',
    trailerModel: '',
    weight: '',
    length: '',
  },
  submitted: false,
}

const vehicleSlice = createSlice({
  name: 'vehicle',
  initialState,
  reducers: {
    setVehicleType: (state, action: PayloadAction<VehicleType>) => {
      state.vehicleType = action.payload
    },
    updateCar: (state, action: PayloadAction<Partial<CarData>>) => {
      state.car = { ...state.car, ...action.payload }
    },
    updateTruck: (state, action: PayloadAction<Partial<TruckData>>) => {
      state.truck = { ...state.truck, ...action.payload }
    },
    submitVehicle: (state) => {
      state.submitted = true
    },
    resetVehicle: () => initialState,
  },
})

export const { setVehicleType, updateCar, updateTruck, submitVehicle, resetVehicle } = vehicleSlice.actions
export default vehicleSlice.reducer