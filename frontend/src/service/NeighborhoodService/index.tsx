import { NeighborhoodDTO } from "../../dto";
import api from "../api";

const NeighborhoodService = {
  async getAllNeighborhoods(): Promise<NeighborhoodDTO[]> {
    const response = await api.get("/neighborhoods");
    return response.data;
  },

  async getNeighborhoodById(id: number): Promise<NeighborhoodDTO> {
    const response = await api.get(`/neighborhoods/${id}`);
    return response.data;
  },

  async updateNeighborhood(
    id: number,
    data: NeighborhoodDTO,
  ): Promise<NeighborhoodDTO> {
    const response = await api.put(`/neighborhoods/${id}`);
    return response.data;
  },

  async createNeighborhood(data: NeighborhoodDTO): Promise<NeighborhoodDTO> {
    const response = await api.post(`/neighborhoods`, data);
    return response.data;
  },
};

export default NeighborhoodService;
