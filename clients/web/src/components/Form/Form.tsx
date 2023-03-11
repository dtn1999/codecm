import React from "react";
import { FormProvider, SubmitHandler, UseFormReturn } from "react-hook-form";

interface FormProps {
  methods: UseFormReturn<any, any>;
  onSubmit: SubmitHandler<any>;
  children: React.ReactNode;
}

export const Form: React.FC<FormProps> = ({ methods, children, onSubmit }) => {
  return (
    <FormProvider {...methods}>
      <form onSubmit={methods.handleSubmit(onSubmit)}>{children}</form>
    </FormProvider>
  );
};
