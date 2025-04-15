import Draggable from "react-draggable";
import { useRef } from "react";
import { XMarkIcon } from "@heroicons/react/24/solid";

interface ConfirmDeleteModalProps {
  title?: string;
  message?: string;
  onConfirm: () => void;
  onCancel: () => void;
}

export default function ConfirmDeleteModal({
  title = "Confirmar exclusão",
  message = "Tem certeza que deseja excluir este item?",
  onConfirm,
  onCancel,
}: ConfirmDeleteModalProps) {
  const nodeRef = useRef<HTMLDivElement>(null!);

  return (
    <div className="confirm-delete-modal-overlay">
      <Draggable handle=".modal-header" nodeRef={nodeRef}>
        <div ref={nodeRef} className="confirm-delete-modal relative">
          <div className="modal-header flex justify-between items-center">
            <span>{title}</span>
            <XMarkIcon
              onClick={onCancel}
              className="w-5 h-5 text-gray-500 dark:text-gray-300 hover:text-red-500 cursor-pointer transition"
            />
          </div>

          <p>{message}</p>

          <div className="flex justify-center gap-4 mt-6">
            <button onClick={onCancel} className="btn-cancel">
              Cancelar
            </button>
            <button onClick={onConfirm} className="btn-confirm">
              Excluir
            </button>
          </div>
        </div>
      </Draggable>
    </div>
  );
}
