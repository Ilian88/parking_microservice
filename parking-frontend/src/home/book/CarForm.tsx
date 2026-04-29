import { Field, SectionLabel, SelectField} from "../Book";
import { euroOptions } from "./constants";
import { type CarErrors, type CarFormType } from "./types";


export default function CarForm({car, setCar_, carErrors, handleSubmit}: {
    car: CarFormType,
    setCar_: (key: keyof CarFormType)   => (val: string) => void
    carErrors: CarErrors,
    handleSubmit: ()=> void
}) {
    return (
          <div className="flex flex-col gap-5">
            <SectionLabel>Vehicle details</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="License plate" id="licensePlate"
                value={car.licensePlate} onChange={setCar_('licensePlate')}
                error={carErrors.licensePlate} placeholder="CA 1234 AB" required
              />
              <SelectField
                label="Euro category" id="euroCategory"
                value={car.euroCategory} onChange={setCar_('euroCategory')}
                error={carErrors.euroCategory} options={euroOptions}
                placeholder="Select category" required
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Make" id="make"
                value={car.make} onChange={setCar_('make')}
                error={carErrors.make} placeholder="e.g. Toyota"
              />
              <Field
                label="Model" id="model"
                value={car.model} onChange={setCar_('model')}
                error={carErrors.model} placeholder="e.g. Corolla"
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