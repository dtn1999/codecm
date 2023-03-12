import {
  DialogWrapper,
  Form,
  FormInput,
  FormTextarea,
  LoadingDots,
} from "@we/components";
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
  template: Template;
  createPlayground: (request: CreatePlaygroundInput) => Promise<void>;
}

export const CreatePlaygroundDialog: React.FC<Props> = ({
  isOpen,
  closeModal,
  title,
  template,
  createPlayground,
}) => {
  const [loading, setLoading] = React.useState(false);
  const methods = useForm<CreatePlaygroundInput>({
    resolver: zodResolver(CreatePlaygroundInputSchema),
    mode: "all",
    defaultValues: {
      name: template.name,
      imageUrl: template.imageUrl,
      githubRepoUrl: template.githubRepoUrl,
      description: "simple description of what this playground is about",
    },
  });
  const {
    formState: { isSubmitting },
  } = methods;
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
          setLoading(true);
          await createPlayground(data);
          setLoading(false);
        }}
      >
        <div className="p-4">
          <FormInput
            name="name"
            required
            placeholder="enter a title for the playground"
          />
          <FormTextarea
            name="description"
            required
            placeholder="description"
            row={5}
          />
        </div>
        <div className="flex w-full items-center justify-end px-4 py-4">
          <button
            type="submit"
            disabled={loading}
            className={cn(
              "space-x-2 rounded border py-2 px-3 font-light capitalize text-white",
              {
                "border-transparent bg-sky-700 hover:bg-sky-600": !loading,
                "pointer-events-none cursor-not-allowed border-gray-200 bg-background":
                  loading,
              }
            )}
          >
            {loading && <LoadingDots />}
            <span>create playground</span>
          </button>
        </div>
      </Form>
    </DialogWrapper>
  );
};
