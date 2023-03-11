import { DialogWrapper, Form, FormInput, FormTextarea } from "@we/components";
import { DialogProps } from "@we/types/ui";
import React from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import {
  CreatePlaygroundInput,
  CreatePlaygroundInputSchema,
} from "@we/types/schemas";

export const CreatePlaygroundDialog: React.FC<
  Omit<DialogProps, "children">
> = ({ isOpen, closeModal, title }) => {
  const methods = useForm<CreatePlaygroundInput>({
    resolver: zodResolver(CreatePlaygroundInputSchema),
    mode: "all",
  });

  return (
    <DialogWrapper isOpen={isOpen} closeModal={closeModal} title={title}>
      <Form
        methods={methods}
        onSubmit={async (data) => {
          console.log(data);
        }}
      >
        <div className="p-4">
          <FormInput name="title" placeholder="Test Playground" />
          <FormTextarea
            name="description"
            placeholder="Test Playground Description"
            row={5}
          />
        </div>
      </Form>
    </DialogWrapper>
  );
};
