import api from "../api/index";
import { AddressDTO } from "../../dto";

const AddressService = {
  async createAddress(data: AddressDTO): Promise<AddressDTO> {
    const response = await api.post("/addresses", data);
    return response.data;
  },

  async getAllAddresses(): Promise<AddressDTO[]> {
    const response = await api.get("/addresses");
    return response.data;
  },

  async getAddressById(id: number): Promise<AddressDTO> {
    const response = await api.get(`/addresses/${id}`);
    return response.data;
  },

  async updateAddress(id: number, data: AddressDTO): Promise<AddressDTO> {
    const response = await api.put(`/addresses/${id}`, data);
    return response.data;
  },

  async deleteAddress(id: number): Promise<void> {
    await api.delete(`/addresses/${id}`);
  },
};

export default AddressService;
