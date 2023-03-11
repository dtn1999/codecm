import React from "react";
import cn from "classnames";
import { useFormContext } from "react-hook-form";

interface Props {
  name: string;
  label?: string;
  placeholder?: string;
  required?: boolean;
  type?: HTMLInputElement["type"];
}

export const FormInput: React.FC<Props> = ({
  name,
  placeholder,
  required,
  type,
  label,
}) => {
  const { register, getFieldState } = useFormContext();
  const { error, isDirty, isTouched } = getFieldState(name);
  if (!name) {
    return null;
  }

  return (
    <div className={cn("w-full flex flex-col")}>
      <label htmlFor={name} className="capitalize text-black">
        {label} {required && <span className="text-red-500">*</span>}
      </label>
      <input
        {...register(name, { required: required ? true : false })}
        name={name}
        type={type?.toString().toLowerCase()}
        placeholder={placeholder || ""}
        className={cn(
          "w-full focus:outline-none placeholder:text-black placeholder:font-light text-sm py-3 px-4 border border-black my-2 text-black",
        )}
      />
      {error && <p className="font-extralight text-red-500">{error.message}</p>}
    </div>
  );
};