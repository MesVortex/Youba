import { api } from "./api";

export const getCustomers = async () => {
    const response = await api.get("/customers");
    return response.data;
};

export const addCustomer = async (customer: { name: string; email: string }) => {
    const response = await api.post("/customers", customer);
    return response.data;
};
