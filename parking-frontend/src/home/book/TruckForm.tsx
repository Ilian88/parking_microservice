import { Field, SectionLabel, SelectField} from "../Book";
import { euroOptions } from "./constants";
import {type TruckErrors, type TruckFormType } from './types'


export default function TruckForm({truck, setTruck_, truckErrors, handleSubmit }: {
    truck: TruckFormType,
    setTruck_: (key: keyof TruckFormType)   => (val: string) => void,
    truckErrors: TruckErrors,
    handleSubmit: ()=> void
}) {

    return (
          <div className="flex flex-col gap-5">
            <SectionLabel>Common details</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="License plate" id="licensePlate"
                value={truck.licensePlate} onChange={setTruck_('licensePlate')}
                error={truckErrors.licensePlate} placeholder="CA 1234 AB" required
              />
              <SelectField
                label="Euro category" id="euroCategory"
                value={truck.euroCategory} onChange={setTruck_('euroCategory')}
                error={truckErrors.euroCategory} options={euroOptions}
                placeholder="Select category" required
              />
            </div>

            <SectionLabel>Truck</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Truck make" id="truckMake"
                value={truck.truckMake} onChange={setTruck_('truckMake')}
                error={truckErrors.truckMake} placeholder="e.g. Volvo"
              />
              <Field
                label="Truck model" id="truckModel"
                value={truck.truckModel} onChange={setTruck_('truckModel')}
                error={truckErrors.truckModel} placeholder="e.g. FH16"
              />
            </div>

            <SectionLabel>Trailer</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Trailer make" id="trailerMake"
                value={truck.trailerMake} onChange={setTruck_('trailerMake')}
                error={truckErrors.trailerMake} placeholder="e.g. Schmitz"
              />
              <Field
                label="Trailer model" id="trailerModel"
                value={truck.trailerModel} onChange={setTruck_('trailerModel')}
                error={truckErrors.trailerModel} placeholder="e.g. S.KO"
              />
            </div>

            <SectionLabel>Composition</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Total weight (kg)" id="weight"
                type="number" value={truck.weight} onChange={setTruck_('weight')}
                error={truckErrors.weight} placeholder="e.g. 40000" required
              />
              <Field
                label="Total length (m)" id="length"
                type="number" value={truck.length} onChange={setTruck_('length')}
                error={truckErrors.length} placeholder="e.g. 18.75" required
              />
            </div>

            <p className="text-[12px] text-[#1a1a18]/35 font-light">
              Fields marked with <span className="text-red-400">*</span> are required.
            </p>

            <button
              onClick={handleSubmit}
              className="w-full bg-[#1a1a18] text-[#f7f6f2] py-4 rounded-sm text-[15px] font-medium hover:bg-[#333330] hover:-translate-y-px active:translate-y-0 transition-all mt-2"
            >
              Confirm booking
            </button>
          </div>
        )
}