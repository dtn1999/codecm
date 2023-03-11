import { DialogWrapper, Form, FormInput, FormTextarea } from "@we/components";
import { DialogProps } from "@we/types/ui";
import React from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import cn from "classnames";
import Image from "next/image";
import {
  CreatePlaygroundInput,
  CreatePlaygroundInputSchema,
  Template,
} from "@we/types/schemas";

interface Props extends Omit<DialogProps, "children"> {
  template: Omit<Template, "id">;
}

export const CreatePlaygroundDialog: React.FC<Props> = ({
  isOpen,
  closeModal,
  title,
  template,
}) => {
  const methods = useForm<CreatePlaygroundInput>({
    resolver: zodResolver(CreatePlaygroundInputSchema),
    mode: "all",
  });

  return (
    <DialogWrapper isOpen={isOpen} closeModal={closeModal} title={title}>
      {template && (
        <div className="transparent relative mx-4 rounded p-3 font-thin">
          <Image
            src={template.imageUrl}
            alt={template.name}
            width={100}
            height={100}
            className="my-2 object-contain mix-blend-lighten"
          />
          <h3 className="py-2 text-sm font-medium leading-5 text-white">
            {template.name}
          </h3>
          <p className="pt-2 text-white">{template.description}</p>
        </div>
      )}
      <Form
        methods={methods}
        onSubmit={async (data) => {
          console.log(data);
        }}
      >
        <div className="p-4">
          <FormInput
            name="title"
            placeholder="enter a title for the playground"
          />
          <FormTextarea name="description" placeholder="description" row={5} />
        </div>
        <div className="flex w-full items-center justify-end px-4 py-4">
          <button
            type="submit"
            className="rounded bg-sky-700 py-2 px-3 font-light capitalize text-white hover:bg-sky-600"
          >
            create playground
          </button>
        </div>
      </Form>
    </DialogWrapper>
  );
};
