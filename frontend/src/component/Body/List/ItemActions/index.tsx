import { PencilIcon, TrashIcon } from "@heroicons/react/24/solid";
import AddressService from "../../../../service/AddressService";
import { useAddressContext } from "../../../../context/AddressContext";
import { useModal } from "../../../../hook/useModal";
import ConfirmDeleteModal from "../Address/ConfirmDeleteModal";
import { useState } from "react";

interface ItemActionsProps {
  id: number;
  entityType?:
    | "addresses"
    | "tracts"
    | "tract-owners"
    | "third-parties"
    | "quotes";
  onEdit?: (id: number | undefined) => void;
  onDeleted?: () => void;
}

export default function ItemActions({
  id,
  entityType,
  onEdit,
}: ItemActionsProps) {
  const { isOpen, open, close } = useModal();
  const { refreshAddresses } = useAddressContext();

  const handleDelete = async () => {
    try {
      await AddressService.deleteAddress(id);
      refreshAddresses();
    } catch (error) {
      alert("Erro ao deletar item.");
    } finally {
      close();
    }
  };

  return (
    <>
      <div className="itemActions flex gap-2">
        {onEdit && <PencilIcon className="pencil" onClick={() => onEdit(id)} />}
        <TrashIcon className="trash" onClick={open} />
      </div>
      {isOpen && (
        <ConfirmDeleteModal
          message="Este item será removido permanentemente. Deseja continuar?"
          onCancel={close}
          onConfirm={handleDelete}
        />
      )}
    </>
  );
}
