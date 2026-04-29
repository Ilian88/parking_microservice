export type VehicleType = 'car' | 'truck'

export type EuroCategory = 'Euro 1' | 'Euro 2' | 'Euro 3' | 'Euro 4' | 'Euro 5' | 'Euro 6'

export type CarFormType = {
  licensePlate: string
  euroCategory: EuroCategory | ''
  make: string
  model: string
}

export type TruckFormType = {
  licensePlate: string
  euroCategory: EuroCategory | ''
  truckMake: string
  truckModel: string
  trailerMake: string
  trailerModel: string
  weight: string
  length: string
}

export type CarErrors   = Partial<Record<keyof CarFormType,   string>>
export type TruckErrors = Partial<Record<keyof TruckFormType, string>>