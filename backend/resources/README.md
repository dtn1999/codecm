# RESOURCES SERVICE

This service is responsible for managing application resources:

- [VMS](#vms) 
- [Templates](#templates)

## VMS

Each user of the application can create virtual machines (VMs) that will be used to run their applications,
or run and experiment with a specific template.

```ts
// create VM request
interface VMResource {
    id: string
    name: string;
    size: string;
    state: VMState;
}

interface VMSTate{
    CREATING,
    CREATED,
    RUNNING,
    STOPPING,
    STOPPED
}

interface CreateVMRequest {
    templateId: string;
}
```

## Templates

A template project represents a starter project that can be used to create a experiment a new technology or 
or framework. The templates have the following structure:

```ts
interface Template {
    id: string;
    name: string;
    description: string;
    githubUrl: string;
    tags: string[];
    image: string;
}
```
Template endpoints:

`base-url=/api/v1/resources`
```yaml
- GET /templates
  - description: Get all templates
  - response:
    - 200: OK
    - body:
      - data: Template[]
        error: string
        success: boolean
        size: number
    - 500: Internal Server Error

- GET /templates/{id}
    - description: Get a template by id
    - response:
      - 200: OK
      - body:
        - data: Template
          error: string
          success: boolean
          size: number
      - 404: Not Found
      - 500: Internal Server Error

- POST /templates
    - description: Create a new template
    - body:
      - data: Template
    - response:
      - 201: Created
      - body:
        - data: Template
          error: string
          success: boolean
          size: number
      - 400: Bad Request
      - 500: Internal Server Error

- PUT /templates/{id}
    - description: Update a template
    - body:
      - data: Template
    - response:
      - 200: OK
      - body:
        - data: Template
          error: string
          success: boolean
          size: number
      - 400: Bad Request
      - 404: Not Found
      - 500: Internal Server Error
  
- DELETE /templates/{id}
    - description: Delete a template
    - response:
      - 200: OK
      - body:
        - data: Template
          error: string
          success: boolean
          size: number
      - 404: Not Found
      - 500: Internal Server Error
```